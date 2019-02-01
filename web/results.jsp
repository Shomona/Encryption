<%-- 
    Document   : results
    Created on : Jan 31, 2019, 8:27:14 PM
    Author     : shomonamukherjee
--%>

<%-- 
    Document   : index
    Created on : Jan 28, 2019, 11:10:54 AM
    Author     : shomonamukherjee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task1</title>
    </head>
    <body>
        The encryption in Base 64 encoding <%= request.getAttribute("hash64") %> <br>
            The encryption in Hexadecimal encoding <%= request.getAttribute("hashHex") %> <br> <br>
                
        <form action="getHash" method="GET">
            <fieldset>
                Enter a string:<br>
                <input type="text" name="inputstring" ><br><br>
                <label>Select Encoding:</label> <br>
                <input type="radio" name="encoding" value="MD5" checked> MD5<br>
                <input type="radio" name="encoding" value="SHA-256" > SHA-256 <br>
                <br><br>
                <input type="submit" value="Submit">
            </fieldset>
        </form>
    </body>
</html>
