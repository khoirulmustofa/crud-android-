<?php
require_once '../db_connection.php';

//buat array untuk menampung respon dari JSON
$respon = array();

// cek apakah variabel idmem sudah terset / terisi
if (isset($_GET["id"])) {
	$id = $_GET['id'];

	// query ambil data member berdasarkan id
	$sql="SELECT
  `id`,
  `name`,
  `address`,
  `phone`,
  `email`
FROM `inventory_km`.`customer`
WHERE `id` = '$id'";
	$result = mysqli_query($con, $sql);

	if (!empty($result)) {
		// jika data member ada (besar dari nol)
		if (mysql_num_rows($result) > 0) {
			$result = mysqli_fetch_array($result);
			// temp member array
			$customer = array();
			$customer["id"] = $result["id"];
			$customer["nama"] = $result["nama"];
			$customer["alamat"] = $result["alamat"];

			// sukses
			$respon["sukses"] = 1;

			// node member
			$respon["member"] = array();
			//tambahkan array $member pada array final $respon
			array_push($respon["member"], $member);

			// memprint/mencetak JSON respon
			echo json_encode($respon);
		} else {
			// tidak ada member (kecil dari nol)
			$respon["sukses"] = 0;
			$respon["pesan"] = "Tidak ada member";

			// memprint/mencetak JSON respon
			echo json_encode($respon);
		}
	} else {
		// jika query tidak tidak meghasilkan data (tidak ada member)
		$respon["sukses"] = 0;
		$respon["pesan"] = "tidak ada member";

		// memprint/mencetak JSON respon
		echo json_encode($respon);
	}
} else {
	// jika data tidak terisi/tidak terset
	$respon["sukses"] = 0;
	$respon["pesan"] = "data belum terisi";

	// memprint/mencetak JSON respon
	echo json_encode($respon);
}