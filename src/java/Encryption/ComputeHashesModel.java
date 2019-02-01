/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author shomonamukherjee
 */
public class ComputeHashesModel {

    public byte[] computeHash(String inputString, String hashType) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //Specify the encoding and get the byte stream
        MessageDigest md = MessageDigest.getInstance(hashType);
        byte[] hashedBytes = md.digest(inputString.getBytes("UTF-8"));
        return hashedBytes;

    }
}
