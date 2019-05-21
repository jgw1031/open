<?php
    error_reporting(E_ALL);
    ini_set('display_errors',1);
    include('dbcon.php');


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit']))
    {
        $ID=$_POST['ID'];
        $PASSWORD=$_POST['PASSWORD'];
        $name=$_POST['NAME'];
        $AGE=$_POST['AGE'];
        $PHONE=$_POST['PHONE'];
        $GENDER=$_POST['GENDER'];
        if(empty($ID)){
            $errMSG = "아이디.";
        }
        else if(empty($PASSWORD)){
            $errMSG = "비밀번호";
        }
        else if(empty($name)){
            $errMSG = "이름";
          }
        else if(empty($GENDER)){
            $errMSG = "성별";
        }
        else if(empty($PHONE)){
            $errMSG = "휴대전화";
        }
        else if(empty($AGE)){
            $errMSG = "나이";
        }
        if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO user(NAME,ID,PASSWORD,GENDER,PHONE,AGE) VALUES(:NAME,:ID,:PASSWORD,:GENDER,:PHONE,:AGE)');
                $stmt->bindParam(':NAME', $name);
                $stmt->bindParam(':ID', $ID);
	               $stmt->bindParam(':PASSWORD',$PASSWORD);
                $stmt->bindParam(':GENDER', $GENDER);
                $stmt->bindParam(':PHONE', $PHONE);
                $stmt->bindParam(':AGE', $AGE);
                if($stmt->execute())
                {
                    $successMSG = "새로운 사용자를 추가했습니다.";
                }
                else

                {

                    $errMSG = "사용자 추가 에러";

                }



            } catch(PDOException $e) {

                die("Database error: " . $e->getMessage());

            }

        }



}

?>

<?php
	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
    if( !$android )
    {
?>
<html>
 <body>
        <?php
        //header('Content-Type: text/html; charset=euc-kr');
        if (isset($errMSG)) echo $errMSG;
        if (isset($successMSG)) echo $successMSG;
        ?>
        <form action="<?php $_PHP_SELF ?>" method="POST">
            이름: <input type = "text" name = "NAME" />
            아이디: <input type = "text" name = "ID" />
            비밀번호: <input type = "text" name = "PASSWORD" />
            성별: <input type = "text" name = "GENDER" />
            핸드폰번호: <input type = "text" name = "PHONE" />
            나이: <input type = "int" name = "AGE" />
            <input type = "submit" name = "submit" />
        </form>
   </body>
</html>
<?php
    }
?>
