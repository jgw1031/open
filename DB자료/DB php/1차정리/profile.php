<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
include('dbcon.php');
//POST 값을 읽어온다.
$ID=isset($_POST['ID']) ? $_POST['ID'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
if ($ID != "" ){
    $sql="SELECT * FROM user U,review R where U.ID='$ID'AND R.TARGETID ='$ID' ";
    $stmt = $con->prepare($sql);
    $stmt->execute();
    if ($stmt->rowCount() == 0){
        echo "'";
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
	                 'AGE'=>$row["AGE"],
		'STAR'=>$row["STAR"],
	    "TEXT"=>$row["TEXT"],
	    "ID2"=>$row["ID2"]
	    
            ));
        }
        if (!$android) {
            echo "<pre>";
            print_r($data);
            echo '</pre>';
        }else
        {
            echo json_encode(array("Result"=>$data));
        }
    }
}
else {
    echo "아이디를 입력하세요  ";
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
         <input type = "submit" />
      </form>
   </body>
</html>
<?php
}
?>
