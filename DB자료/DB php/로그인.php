﻿<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
//POST 값을 읽어온다.
$ID=isset($_POST['ID']) ? $_POST['ID'] : '';
$PASSWORD = isset($_POST['PASSWORD']) ? $_POST['PASSWORD'] : '';
$name = isset($_POST['name']) ? $_POST['name'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
if ($ID != "" ){
    $sql="SELECT * FROM user where ID='$ID'AND PASSWORD='$PASSWORD'";
    $stmt = $con->prepare($sql);
    $stmt->execute();
    if ($stmt->rowCount() == 0){
        echo "'";
        echo $ID,", ",$PASSWORD;
        echo "'은 찾을 수 없습니다.";
    }
	else{
   		$data = array();
        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
        	extract($row);
            array_push($data,
                array('ID'=>$row["ID"],
                'NAME'=>$row["NAME"],
                'PASSWORD'=>$row["PASSWORD"],
	    'GENDER'=>$row["GENDER"],
	    'PHONE'=>$row["PHONE"],
	    'AGE'=>$row["AGE"]
            ));
        }
        if (!$android) {
            echo "<pre>";
            print_r($data);
            echo '</pre>';
        }else
        {
            $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
            echo $json;
        }
    }
}
else {
    echo "아이디와 비밀번호를 입력하세요  ";
}

?>
<?php
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
if (!$android){
?>
<html>
   <body>
      <form action="<?php $_PHP_SELF ?>" method="POST">
         아이디: <input type = "text" name = "ID" />
         비밀번호: <input type = "text" name = "PASSWORD" />
         <input type = "submit" />
      </form>
   </body>
</html>
<?php
}
?>