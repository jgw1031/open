<?php
    $host = "localhost";      //아이피주소
    $dbname = "dbroot";      //데이터 베이스 이름
    $username = "root";          // DB 아이디
    $password = "1234";        // DB 비밀번호
    $dbChar = "utf-8";            // 언어 설정 
    $options = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SETNAMES utf-8');
    try {
        $con = new PDO("mysql:host={$host};dbname={$dbname};setnames=utf-8",$username, $password);
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
