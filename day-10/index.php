<?php

if ($argc != 2) {
  echo "Usage: php index.php input_file_name";
  exit(1);
}

$file = fopen($argv[1], "r") or die("Unable to open file!");
$lines = [];

while (!feof($file)) {
  array_push($lines, fgets($file));
}

fclose($file);

class Bot
{
  public $values = [];

  public function __construct(
    public string $id,
    public string $lowType,
    public string $lowId,
    public string $highType,
    public string $highId
  ) {
    $this->values = [];
  }
}

$bots = [];
$value_lines = [];
$outputs = [];

foreach ($lines as $line) {
  $words = explode(" ", $line);
  if (str_starts_with($line, "value")) {
    array_push($value_lines, [$words[5], $words[1]]);
    continue;
  }

  $bots[(int)$words[1]] = new Bot(
    $words[1],
    $words[5],
    (int)$words[6],
    $words[10],
    (int)$words[11]
  );
}

function process()
{
  global $bots;
  global $outputs;
  global $x1;

  $changes = 0;

  foreach ($bots as $_ => $bot) {
    if (count($bot->values) === 2) {
      $low = min($bot->values);
      $high = max($bot->values);

      $can_move = ($bot->lowType === "bot" ? count($bots[$bot->lowId]->values) < 2 : true)
        && ($bot->highType === "bot" ? count($bots[$bot->highId]->values) < 2 : true);

      if ($can_move) {
        $changes++;

        if ($low === 17 && $high === 61 && $x1 === -1) {
          $x1 = $bot->id;
        }

        if ($bot->lowType === "bot") {
          array_push($bots[$bot->lowId]->values, $low);
        } else {
          if (!in_array($bot->lowId, $outputs)) {
            $outputs[$bot->lowId] = [];
          }

          array_push($outputs[$bot->lowId], $low);
        }

        if ($bot->highType === "bot") {
          array_push($bots[$bot->highId]->values, $high);
        } else {
          if (!in_array($bot->highId, $outputs)) {
            $outputs[$bot->highId] = [];
          }

          array_push($outputs[$bot->highId], $high);
        }

        $bot->values = [];
      }
    }
  }

  return $changes > 0;
}

$x1 = -1;
$done = [];

while (count($value_lines) > count($done)) {
  foreach ($value_lines as $val) {
    $bot_id = (int) $val[0];
    $value = (int) $val[1];

    if (in_array("$bot_id-$value", $done)) {
      continue;
    }

    if (count($bots[$bot_id]->values) < 2) {
      array_push($bots[$bot_id]->values, $value);
      array_push($done, "$bot_id-$value");
    }

    while (process()) {
    };
  }
}

$x2 = 1;
for ($i = 0; $i < 3; $i++) {
  $x2 *= $outputs[$i][0];
}

echo "x1: $x1\n";
echo "x2: $x2\n";
