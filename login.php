<?php include_once("login.html"); ?>
<!DOCTYPE html>
<html>
<title>Login Page Pop Up</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">



<body>

<!--Navbar On Top-->

<nav>
    <div class="logo">
        <h4>Login Form</h4>
    </div>
    <ul class="nav-links">
        <li>
            <a href="#">Home</a>
        </li>

        <li>
            <a href="#">About</a>
        </li>

        <li>
            <a href="#">Statis</a>
        </li>

        <li>
            <a href="#">Form</a>
        </li>
    </ul>
    
    </div>

</nav>

<!-- Login Form Center -->
<div class="center">
  <h1>Sign In</h1>


  <form method="post">
    <div class="txt_field">
      <input type="text" required>
      <span></span>
      <label>Username</label>
    </div>

    <form method="post">
      <div class="txt_field">
        <input type="text" required>
        <span></span>
        <label>Password</label>
      </div>

      <div class="pass">Forgot Password?</div>
        <input type="submit" value="Login">
        <div class="signup_link">
          Not a member? <a href="#">Signup</a>
        </div>
      </form>
    </div>











</body>
</html>
