<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);
    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {
        $ID=$_POST['ID'];
        $TARGETID=$_POST['TARGETID'];
        $TEXT=$_POST['TEXT'];
        $STAR=$_POST['STAR'];
        $AREA=$_POST['AREA'];
        $TIMES=$_POST['TIMES'];
        if(empty($ID)){
            $errMSG = "아이디";
        }
        else if(empty($TARGETID)){
            $errMSG = "대상아이디";
        }
        else if(empty($TEXT)){
            $errMSG = "내용";
          }
          else if(empty($STAR)){
              $errMSG = "별점";
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
                $stmt = $con->prepare('INSERT INTO review(ID,TARGETID,TEXT,STAR,AREA,TIMES) VALUES(:ID,:TARGETID,:TEXT,:STAR,:AREA,:TIMES)');
                $stmt->bindParam(':ID', $ID);
                $stmt->bindParam(':TARGETID', $TARGETID);
	              $stmt->bindParam(':TEXT',$TEXT);
	              $stmt->bindParam(':STAR', $STAR);
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
              대상아이디: <input type = "text" name = "TARGETID" />
              별점 : <input type = "text" name = "STAR" />
              내용: <input type = "text" name = "TEXT" />
	            지역: <input type = "text" name = "AREA" />
              시간: <input type = "text" name = "TIMES" />
                <input type = "submit" name = "submit" />
            </form>

       </body>
    </html>

<?php
    }
?>
