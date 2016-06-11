<?php
define('HOST','localhost'); 
define('USER','root'); 
define('PASS',''); 
define('DB','inventory_km'); 
//Connecting to Database 
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

