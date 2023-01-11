# frozen_string_literal: true

class AssemBunny
  attr_reader :reg

  def initialize(reg, instructions)
    @reg = reg
    @instructions = instructions
  end

  def run
    pointer = 0

    while pointer < @instructions.length
      ins = @instructions[pointer]

      case (ins[0])
      when 'cpy'
        @reg[ins[2].to_sym] = cpy(ins)
      when 'inc'
        @reg[ins[1].to_sym] += 1
      when 'dec'
        @reg[ins[1].to_sym] -= 1
      when 'jnz'
        if @reg[ins[1].to_sym] != 0
          pointer += ins[2].to_i
          next
        end
      end

      pointer += 1
    end
  end

  private

  def cpy(ins)
    return ins[1].to_i if ins[1].match(/^(\d)+$/)
    @reg[ins[1].to_sym]
  end
end

instructions = $stdin.readlines.map { |line| line.chomp.split(' ') }

x1 = AssemBunny.new({ a: 0, b: 0, c: 0, d: 0 }, instructions)
x1.run
p x1.reg[:a]

x2 = AssemBunny.new({ a: 0, b: 0, c: 1, d: 0 }, instructions)
x2.run
p x2.reg[:a]
