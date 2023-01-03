#include <iostream>
#include <vector>

bool is_triangle(int a, int b, int c)
{
  return a + b > c && a + c > b && b + c > a;
}

int main()
{
  int x1 = 0;
  int x2 = 0;

  int a, b, c;
  std::vector<std::vector<int>> triangles;

  while (std::cin >> a >> b >> c)
  {
    triangles.push_back({a, b, c});
    if (is_triangle(a, b, c))
    {
      x1++;
    }
  }

  for (int i = 0; i < triangles.size(); i += 3)
  {
    for (int col = 0; col < 3; col++)
    {
      if (is_triangle(triangles[i][col], triangles[i + 1][col], triangles[i + 2][col]))
      {
        x2++;
      }
    }
  }

  std::cout << x1 << std::endl;
  std::cout << x2 << std::endl;

  return 0;
}