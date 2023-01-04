#include <ctype.h>

#include <iostream>
#include <map>
#include <vector>

int main() {
    int x1 = 0;
    int x2 = -1;

    std::string row;

    while (std::cin >> row) {
        std::map<char, int> map;
        std::string checksum;
        int id = 0;
        int mode = 0;

        for (auto const &chr : row) {
            if (chr == '-') continue;
            if (chr == ']') break;
            if (chr == '[') mode = 1;

            if (isdigit(chr)) {
                id *= 10;
                id += chr - '0';
            } else if (mode) {
                checksum += chr;
            } else {
                ++map[chr];
            }
        }

        if (x2 == -1) {
            std::string decrypted = row.substr(0, row.find('['));
            for (int i = 0; i < id; ++i) {
                for (int j = 0; j < decrypted.size(); ++j) {
                    if (std::isalpha(decrypted[j])) {
                        decrypted[j]++;
                        if (decrypted[j] > 'z') decrypted[j] = 'a';
                    }
                }
            }

            if (decrypted.find("northpole-object-storage") != std::string::npos) {
                x2 = id;
            }
        }

        std::vector<std::pair<char, int>> vec(map.begin(), map.end());
        std::sort(vec.begin(), vec.end(), [](auto a, auto b) {
            if (a.second == b.second) return a.first < b.first;
            return a.second > b.second;
        });

        std::string top5;
        for (int i = 0; i < 5; ++i) {
            top5 += vec[i].first;
        }

        if (top5 == checksum) {
            x1 += id;
        }
    }

    std::cout << x1 << std::endl;
    std::cout << x2 << std::endl;

    return 0;
}