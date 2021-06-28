<?php include_once("login.html");


$server = "localhost";
$user = "root";
$pass = "";
$database = "demo4";

$conn = mysqli_connect($server, $user, $pass, $database);

if (!$conn) {
    die("<script>alert('Connect failed, vui lòng kết nối với database')</script>");
}


session_start();

error_reporting();

if (isset($_SESSION['username'])) {
    header("Location: welcome.php");
}

if (isset($_POST['submit'])) {
    $email = $_POST ['email'];
    $password = md5($_POST['password']);


    $sql = "SELECT *FROM users WHERE email='$email' AND password= '$password' ";
    $result = mysqli_query($conn, $sql);
    if ($result->num_rows > 0) {
        $row = mysqli_fetch_assoc($result);
        $_SESSION['username'] = $row['username'];
        header("Location: welcome.php");
    } else {
        echo "<script>alert('Sai email hoặc password rồi')</script";
    }
}
?>
?>

