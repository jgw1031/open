<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon.php');



//POST 값을 읽어온다.
$ID=isset($_POST['ID']) ? $_POST['ID'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($ID != "" ){

    $sql="SELECT u.GENDER b.* FROM user u board b where user.GENDER='$GENDER'AND board.AREA='$AREA'";
    $stmt = $con->prepare($sql);
    $stmt->execute();

    if ($stmt->rowCount() == 0){
        echo "'";
        echo $GENDER,",",$AREA;
        echo "'은 찾을 수 없습니다.";
    }
	else{

   		$data = array();

        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){

        	extract($row);

            array_push($data,
                array('ID'=>$row["ID"],
                'TITLE'=>$row["TITLE"],
                'CONTENTS'=>$row["CONTENTS"],
                'AREA'=>$row["AREA"],
                'TIME'=>$row["TIME"],
	               'GENDER'=>$row["GENDER"],
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
    echo "  ";
}

?>
<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>

      <form action="<?php $_PHP_SELF ?>" method="POST">
         성별: <input type = "text" name = "GENDER" />
         지역: <input type = "text" name = "AREA" />
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}


?>
