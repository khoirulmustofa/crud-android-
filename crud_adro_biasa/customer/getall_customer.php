<?php
require_once '../db_connection.php';
// buat array untuk menampung respon dari JSON
$respon = array ();

// query menampilkan semua data member
$sql = "SELECT
  `id`,
  `name`,
  `address`,
  `phone`,
  `email`
FROM `inventory_km`.`customer`";
$result = mysqli_query ( $con, $sql ) or die ( mysqli_error ( $con ) );

// jika data ada/tidak kosong
if (mysqli_num_rows ( $result ) > 0) {
	// looping semua hasil
	// member node
	$respon ["customer"] = array ();
	
	while ( $row = mysqli_fetch_array ( $result ) ) {
		// temp member array
		$customer = array ();
		$customer ["id"] = $row ["id"];
		$customer ["name"] = $row ["name"];
		$customer ["address"] = $row ["address"];
		$customer ["phone"] = $row ["phone"];
		$customer ["email"] = $row ["email"];
		
		// tambahkan array $member pada array final $respon
		array_push ( $respon ["customer"], $customer );
	}
	// sukses
	$respon ["sukses"] = 1;
	
	// memprint/mencetak JSON respon
	echo json_encode ( $respon );
} else {
	// jika data kosong
	$respon ["sukses"] = 0;
	$respon ["pesan"] = "Tidak ada member";
	
	// memprint/mencetak JSON respon
	echo json_encode ( $respon );
}