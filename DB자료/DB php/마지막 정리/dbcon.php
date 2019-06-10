<?php
    @header("Content-type:text/html;charset=utf-8");
    $host = "211.225.70.184";  
    $dbname = "dbroot";      
    $username = "root";          
    $password = "1234";        
    $dbChar = "utf-8";           
    $options = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SETNAMES utf-8');

    try {
        $con = new PDO("mysql:host={$host};dbname={$dbname};setnames=utf8",$username, $password);
        $con->exec("set names utf8");
    } catch(PDOException $e) {

        die("Failed to connect to the database: " . $e->getMessage());
    }
    $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $con->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    if(function_exists('get_magic_quotes_gpc') && get_magic_quotes_gpc()) {
        function undo_magic_quotes_gpc(&$array) {
            foreach($array as &$value) {
                if(is_array($value)) {
                    undo_magic_quotes_gpc($value);
                }
                else {
                    $value = stripslashes($value);
                }
            }
        }
        undo_magic_quotes_gpc($_POST);
        undo_magic_quotes_gpc($_GET);
        undo_magic_quotes_gpc($_COOKIE);

    }
?>
