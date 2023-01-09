<?php

$file = fopen("input.txt", "r") or die("Unable to open file!");
$data = fgets($file);
fclose($file);

function decompress($data)
{
  $decompressed = "";
  $i = 0;
  while ($i < strlen($data)) {
    if ($data[$i] == "(") {
      $i++;
      $marker = substr($data, $i, strpos($data, ")", $i) - $i);
      $i += strlen($marker) + 1;

      $marker = explode("x", $marker);
      $length = $marker[0];
      $repeat = $marker[1];
      $decompressed .= str_repeat(substr($data, $i, $length), $repeat);

      $i += $length;
    } else {
      $decompressed .= $data[$i];
      $i++;
    }
  }
  return $decompressed;
}

echo strlen(decompress($data)) . "\n";

function calc_decompression($string)
{
  $i = 0;
  $length = 0;
  while ($i < strlen($string)) {
    if ($string[$i] == "(") {
      $i++;
      $marker = substr($string, $i, strpos($string, ")", $i) - $i);
      $i += strlen($marker) + 1;

      $marker = explode("x", $marker);
      $length += $marker[1] * calc_decompression(substr($string, $i, $marker[0]));

      $i += $marker[0];
    } else {
      $length++;
      $i++;
    }
  }

  return $length;
}

echo calc_decompression($data) . "\n";
