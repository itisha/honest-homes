pragma solidity ^0.4.0;


contract HashArray {

    string[] public hashesArray;

    function addHash(string hash) public returns (uint rowNumber) {
        return hashesArray.push(hash) - 1;
    }

    function getHashesCount() public constant returns (uint entityCount) {
        return hashesArray.length;
    }

    function getHash(uint index) public constant returns (string row) {
        return hashesArray[index];
    }
}