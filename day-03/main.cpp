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
      ++x1;
    }
  }

  for (int row = 0; row < triangles.size(); row += 3)
  {
    for (int col = 0; col < 3; ++col)
    {
      if (is_triangle(triangles[row][col], triangles[row + 1][col], triangles[row + 2][col]))
      {
        ++x2;
      }
    }
  }

  std::cout << x1 << std::endl;
  std::cout << x2 << std::endl;

  return 0;
}