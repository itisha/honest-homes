package com.exadel.ethereum.contractwrapper;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 * <p>
 * <p>Generated with web3j version 2.3.0.
 */
public final class HashArray extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b6105478061001f6000396000f300606060405263ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631415b022811461005e5780636b2fafa9146100ec578063aeb276021461017a578063d53bdf05146101dd575b600080fd5b341561006957600080fd5b610074600435610202565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100b15780820151818401525b602001610098565b50505050905090810190601f1680156100de5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156100f757600080fd5b6100746004356102be565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100b15780820151818401525b602001610098565b50505050905090810190601f1680156100de5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561018557600080fd5b6101cb60046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061038495505050505050565b60405190815260200160405180910390f35b34156101e857600080fd5b6101cb6103c6565b60405190815260200160405180910390f35b600080548290811061021057fe5b906000526020600020900160005b915090508054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102b65780601f1061028b576101008083540402835291602001916102b6565b820191906000526020600020905b81548152906001019060200180831161029957829003601f168201915b505050505081565b6102c66103cd565b60008054839081106102d457fe5b906000526020600020900160005b508054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103775780601f1061034c57610100808354040283529160200191610377565b820191906000526020600020905b81548152906001019060200180831161035a57829003601f168201915b505050505090505b919050565b600060016000805480600101828161039c91906103df565b916000526020600020900160005b508480516103bc929160200190610409565b500390505b919050565b6000545b90565b60206040519081016040526000815290565b81548183558181151161040357600083815260209020610403918101908301610488565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061044a57805160ff1916838001178555610477565b82800160010185558215610477579182015b8281111561047757825182559160200191906001019061045c565b5b506104849291506104b2565b5090565b6103ca91905b808211156104845760006104a282826104d3565b5060010161048e565b5090565b90565b6103ca91905b8082111561048457600081556001016104b8565b5090565b90565b50805460018160011615610100020316600290046000825580601f106104f95750610517565b601f01602090049060005260206000209081019061051791906104b2565b5b505600a165627a7a723058209a239b0eda09f5e82c6986e48e4c883bc7ddf40e38837e86e8cc556eec662a920029";

    private HashArray(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private HashArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Future<HashArray> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(HashArray.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<HashArray> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(HashArray.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static HashArray load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HashArray(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static HashArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HashArray(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Utf8String> hashesArray(Uint256 param0) {
        Function function = new Function("hashesArray",
                Arrays.<Type>asList(param0),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getHash(Uint256 index) {
        Function function = new Function("getHash",
                Arrays.<Type>asList(index),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addHash(Utf8String hash) {
        Function function = new Function("addHash", Arrays.<Type>asList(hash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getHashesCount() {
        Function function = new Function("getHashesCount",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeCallSingleValueReturnAsync(function);
    }
}
