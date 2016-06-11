<?php
require_once '../db_connection.php';

// buat array untuk menampung respon dari JSON
$respon = array ();

// cek apakah nilai yang dikirimkan android sudah terisi
if (isset ( $_POST ['id'] ) && isset ( $_POST ['name'] ) && isset ( $_POST ['address'] ) && isset ( $_POST ['phone'] ) && isset ( $_POST ['email'] )) {
	
	$id = $_POST ['id'];
	$name = $_POST ['name'];
	$address = $_POST ['address'];
	$phone = $_POST ['phone'];
	$email = $_POST ['email'];
	
	// query update berdasarkan id
	$sql = "UPDATE `inventory_km`.`customer`
SET `name` = '$name',
  `address` = '$address',
  `phone` = '$phone',
  `email` = '$email'
WHERE `id` = '$id'";
	$result = mysqli_query ( $con, $sql );
	
	// cek apakah berhasil update atau tidak
	if ($result) {
		// jika sukses diupdate
		$respon ["sukses"] = 1;
		$respon ["pesan"] = "Data customer berhasil diupdate.";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	} else {
		// gagal update data
		$respon ["sukses"] = 0;
		$respon ["pesan"] = "Gagal update data.";
		
		// memprint/mencetak JSON respon
		echo json_encode ( $respon );
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon ["sukses"] = 0;
	$respon ["pesan"] = "data belum terset/terisi";
	
	// memprint/mencetak JSON respon
	echo json_encode ( $respon );
}