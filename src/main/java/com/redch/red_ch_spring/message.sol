// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./YesToken.sol";
import "./NoToken.sol";

contract PredictionMarket {
    address public operator;

    YesToken public yesToken;
    NoToken public noToken;

    uint256 public constant INITIAL_SUPPLY = 50; // 초기 공급량
    uint256 public constant INITIAL_K = INITIAL_SUPPLY * INITIAL_SUPPLY; // 초기 K 값 (50 * 50)
    uint256 public constant TOLERANCE = 1000; // K 값 검증 시 허용할 오차 범위

    uint256 public K; // CPMM의 K 값

    // 1 ether 단위로 관리됨
    uint256 public yesPrice; // "Yes" 토큰의 현재 가격
    uint256 public noPrice;  // "No" 토큰의 현재 가격

    // Cost 이벤트 정의
    event CostCalculated(uint256 cost, string tokenType);

    constructor(address _operator) payable {
        require(msg.value == 100, "Initial amount must be exactly 100 wei");
        operator = _operator;

        // Yes와 No 토큰 초기화
        yesToken = new YesToken("Yes Token", "YES", address(this));
        noToken = new NoToken("No Token", "NO", address(this));

        // 초기 토큰 발행: 각각 50개
        yesToken.mint(address(this), INITIAL_SUPPLY);
        noToken.mint(address(this), INITIAL_SUPPLY);

        // 초기 K 값 설정 (50 * 50)
        K = INITIAL_K;

        // 초기 가격 설정
        updatePrices();
    }

    // 가격을 업데이트하는 내부 함수
    function updatePrices() internal {
        uint256 currentYesSupply = yesToken.totalSupply();
        uint256 currentNoSupply = noToken.totalSupply();

        yesPrice = (currentYesSupply * 1 ether) / currentNoSupply; // "Yes" 토큰의 가격
        noPrice = (currentNoSupply * 1 ether) / currentYesSupply;  // "No" 토큰의 가격
    }

    // "Yes" 토큰 구매 함수: 구매 시 토큰을 추가로 발행함
    function buyYesToken(uint256 yesAmount) external payable {
        require(yesAmount > 0, "Amount must be greater than zero");

        // 현재 가격 업데이트
        updatePrices();

        // 현재 토큰 수량
        uint256 currentYesSupply = yesToken.totalSupply();
        uint256 currentNoSupply = noToken.totalSupply();

        // 새로운 공급량 계산
        uint256 newYesSupply = currentYesSupply + yesAmount;
        uint256 newNoSupply = K / newYesSupply;

        // 필요한 ETH 계산: yesPrice를 이용하여 비용 계산
        uint256 cost = (yesAmount * yesPrice); // yesPrice는 "YES" 토큰의 가격 (wei 단위)
        emit CostCalculated(cost, "YES");
        require(msg.value == cost, "Incorrect ETH amount sent");

        // Yes 토큰을 구매자가 요청한 만큼 발행하고
        yesToken.mint(msg.sender, yesAmount);

        // No 토큰을 태워버림
        uint256 noTokenToBurn = currentNoSupply - newNoSupply;
        noToken.burn(noTokenToBurn);

        // 가격 업데이트
        updatePrices();

        // K 값이 허용된 오차 범위 내에서 유지되는지 확인
        uint256 newK = newYesSupply * newNoSupply;
        require(isWithinTolerance(newK, K, TOLERANCE), "K value must remain within tolerance range");

        // K 값 업데이트
        K = newK;
    }

        // "Yes" 토큰 구매 함수: 구매 시 토큰을 추가로 발행함
    function buyNoToken(uint256 noAmount) external payable {
        require(noAmount > 0, "Amount must be greater than zero");

        // 현재 가격 업데이트
        updatePrices();

        // 현재 토큰 수량
        uint256 currentYesSupply = yesToken.totalSupply();
        uint256 currentNoSupply = noToken.totalSupply();

        // 새로운 공급량 계산
        uint256 newNoSupply = currentNoSupply + noAmount;
        uint256 newYesSupply = K / newNoSupply;

        // 필요한 ETH 계산: yesPrice를 이용하여 비용 계산
        uint256 cost = (noAmount * noPrice); // yesPrice는 "YES" 토큰의 가격 (wei 단위)
        emit CostCalculated(cost, "No");
        require(msg.value == cost, "Incorrect ETH amount sent");

        // Yes 토큰을 구매자가 요청한 만큼 발행하고
        noToken.mint(msg.sender, noAmount);

        // No 토큰을 태워버림
        uint256 yesTokenToBurn = currentYesSupply - newYesSupply;
        yesToken.burn(yesTokenToBurn);

        // 가격 업데이트
        updatePrices();

        // K 값이 허용된 오차 범위 내에서 유지되는지 확인
        uint256 newK = newYesSupply * newNoSupply;
        require(isWithinTolerance(newK, K, TOLERANCE), "K value must remain within tolerance range");

        // K 값 업데이트
        K = newK;
    }


    // 오퍼레이터가 예치된 이더리움을 인출할 수 있는 함수
    function withdraw() external {
        require(msg.sender == operator, "Only operator can withdraw");
        payable(operator).transfer(address(this).balance);
    }

    // "Yes"와 "No" 토큰의 현재 가격을 반환하는 함수
    function getPrices() public view returns (uint256, uint256) {
        return (yesPrice, noPrice);
    }

    // 두 값이 오차 범위 내에서 일치하는지 확인하는 내부 함수
    function isWithinTolerance(uint256 a, uint256 b, uint256 tolerance) internal pure returns (bool) {
        if (a > b) {
            return (a - b) <= tolerance;
        } else {
            return (b - a) <= tolerance;
        }
    }
}

