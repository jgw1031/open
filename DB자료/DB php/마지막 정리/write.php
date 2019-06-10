<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);
    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {
        $ID=$_POST['ID'];
        $TITLE=$_POST['TITLE'];
        $CONTENTS=$_POST['CONTENTS'];
        $AREA=$_POST['AREA'];
        $TIMES=$_POST['TIMES'];
        if(empty($ID)){
            $errMSG = "아이디";
        }
        else if(empty($TITLE)){
            $errMSG = "제목";
        }
        else if(empty($CONTENTS)){
            $errMSG = "내용";
          }
	       else if(empty($AREA)){
            $errMSG = "지역";
          }
	         else if(empty($TIMES)){
            $errMSG = "시간";
          }
        if(!isset($errMSG))
        {
            try{
                // SQL문을 실행하여 데이터를 MySQL 서버의 person 테이블에 저장합니다.
                $stmt = $con->prepare('INSERT INTO board(ID,TITLE,CONTENTS,AREA,TIMES) VALUES(:ID,:TITLE,:CONTENTS,:AREA,:TIMES)');
                $stmt->bindParam(':ID', $ID);
                $stmt->bindParam(':TITLE', $TITLE);
	              $stmt->bindParam(':CONTENTS',$CONTENTS);
	              $stmt->bindParam(':AREA', $AREA);
	              $stmt->bindParam(':TIMES',$TIMES);
                if($stmt->execute())
                {
                    $successMSG = "게시글 등록완료";
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
              제목: <input type = "text" name = "TITLE" />
              내용: <input type = "text" name = "CONTENTS" />
	            지역: <input type = "text" name = "AREA" />
              시간: <input type = "text" name = "TIMES" />
                <input type = "submit" name = "submit" />
            </form>

       </body>
    </html>

<?php
    }
?>
