<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
//POST ���� �о��´�.
$GENDER=isset($_POST['GENDER']) ? $_POST['GENDER'] : '';
$AREA=isset($_POST['AREA']) ? $_POST['AREA'] : '';
$DATA1=isset($_POST['DATA1']) ? $_POST['DATA1'] : '';
$TIME1=isset($_POST['TIME1']) ? $_POST['TIME1'] : '';
$DATA2=isset($_POST['DATA2']) ? $_POST['DATA2'] : '';
$TIME2=isset($_POST['TIME2']) ? $_POST['TIME2'] : '';
$ID=isset($_POST['ID']) ? $_POST['ID'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
if ($GENDER != "" ){
      $sql="SELECT * FROM user U ,board B WHERE U.GENDER='$GENDER'AND B.ID = U.ID AND B.AREA like '%$AREA%'";
    $stmt = $con->prepare($sql);
    $stmt->execute();
    if ($stmt->rowCount() == 0){
        echo "'";
        echo $GENDER,",",$AREA;
    }
	else{
   		$data = array();
        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
        	extract($row);
            array_push($data,
                array('NO'=>$row["NO"],
    	          'ID'=>$row["ID"],
                'TITLE'=>$row["TITLE"],
                'CONTENTS'=>$row["CONTENTS"],
                'AREA'=>$row["AREA"],
	              'GENDER'=>$row["GENDER"],
                'TIMES'=>$row["TIMES"]
            ));
        }
        if (!$android) {
            echo "<pre>";
            print_r($data);
            echo '</pre>';
        }else
        {
            echo json_encode(array("Result"=>$data));
            echo"?";
        }
    }
}
else {

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
         지역:<input type ="text" name ="AREA"/>
         <input type = "submit" />
      </form>
   </body>
</html>
<?php
}


?>
