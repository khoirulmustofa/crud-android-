<?php
require_once '../db_connection.php';
// buat array untuk menampung respon dari JSON
$respon = array ();

// query menampilkan semua data member
$sql = "SELECT
  `id`,
  `nama`,
  `alamat`
FROM `inventory_km`.`member`";
$result = mysqli_query ( $con, $sql ) or die ( mysqli_error ( $con ) );

// jika data ada/tidak kosong
if (mysqli_num_rows ( $result ) > 0) {
	// looping semua hasil
	// member node
	$respon ["member"] = array ();
	
	while ( $row = mysqli_fetch_array ( $result ) ) {
		// temp member array
		$member = array();
        $member["id"] = $row["id"];
        $member["nama"] = $row["nama"];
        $member["alamat"] = $row["alamat"];
				
		// tambahkan array $member pada array final $respon
		array_push ( $respon ["member"], $member );
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