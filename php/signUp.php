<?php
require "DataBase.php";
$db = new DataBase();
if(isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password']))
{
	if($db->dbConnect())
	{
		if($db->signUp("users",$_POST['email'],$_POST['username'],$_POST['password']))
		{
			echo "Sign Up Succes";
		}else echo "Sign Up Failed";
	}else echo "Error: DataBase connection";
}else echo "All fields are required";
?>