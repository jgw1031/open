<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon.php');



//POST 값을 읽어온다.
$ID=isset($_POST['country']) ? $_POST['country'] : '';
$PASSWORD = isset($_POST['PASSWORD']) ? $_POST['PASSWORD'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($ID != "" ){

    $sql="SELECT * FROM user where ID='$ID' OR PASSWORD ='$PASSWORD'";
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
	                'PHONE'=>$row["PHONE"]
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
    echo "검색할 나라를 입력하세요 ";
}

?>



<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>

      <form action="<?php $_PHP_SELF ?>" method="POST">
         나라: <input type = "text" name = "country" />
         이름: <input type = "text" name = "name" />
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}


?>
