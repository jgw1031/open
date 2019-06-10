<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);
    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {
        $ID=$_POST['ID'];
        $TOKEN=$_POST['TOKEN'];
        if(empty($ID)){
            $errMSG = "아이디";
        }
        else if(empty($TOKEN)){
            $errMSG = "토큰";
        }
        if(!isset($errMSG))
        {
            try{
                // SQL문을 실행하여 데이터를 MySQL 서버의 person 테이블에 저장합니다.
                $stmt = $con->prepare('UPDATE user SET TOKEN=:TOKEN WHERE ID =:ID');
                $stmt->bindParam(':ID', $ID);
                $stmt->bindParam(':TOKEN', $TOKEN);
                if($stmt->execute())
                {
                    $successMSG = 푸시 설정변경";
                }
                else
                {
                    $errMSG = "에러";
                }

            } catch(PDOException $e) {
                die("Database error: " . $e->getMessage());
            }
        }

    }

?>
<?php
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

    if( !$android )
    {
?>
    <html>
       <body>

            <form action="<?php $_PHP_SELF ?>" method="POST">
              아이디: <input type = "text" name = "ID" />
              내용: <input type = "text" name = "TOKEN" />
                <input type = "submit" name = "submit" />
            </form>

       </body>
    </html>

<?php
    }
?>
