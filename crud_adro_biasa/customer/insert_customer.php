<?php
require_once '../db_connection.php';
//buat array untuk menampung respon dari JSON
$respon = array();

// cek apakah nilai yang dikirimkan android sudah terisi
if (isset($_POST['name']) && isset($_POST['address'])&& isset($_POST['phone'])&& isset($_POST['email'])) {

	$name = $_POST['name'];
	$address = $_POST['address'];
	$phone = $_POST['phone'];
	$email = $_POST['email'];
	
	// query menambah data customer
	$sql="INSERT INTO `inventory_km`.`customer`
            (`name`,
             `address`,
             `phone`,
             `email`)
VALUES ('$name',
        '$address',
        '$phone',
        '$email')";
	$result = mysqli_query($con, $sql) or die(mysqli_error($con));
	// cek apakah query berhasil menambah data
	if ($result) {
		// jika berhasil menambah data ke mysql
		$respon["sukses"] = 1;
		$respon["pesan"] = "Berhasil menambah data customer.";

		// memprint/mencetak JSON respon
		echo json_encode($respon);
	} else {
		// gagal menambah data customer
		$respon["sukses"] = 0;
		$respon["pesan"] = "Gagal menambah data.";

		// memprint/mencetak JSON respon
		echo json_encode($respon);
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon["sukses"] = 0;
	$respon["pesan"] = "data belum terisi";

	//  memprint/mencetak JSON respon
	echo json_encode($respon);
}