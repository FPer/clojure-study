require 'table'

class Iekei
  TABLE_SIZE = 8

  def initialize
    @table = Table.new([0].cycle(TABLE_SIZE).to_a)
  end

  def comming(customer_numbers)
    customer_numbers.each_char do |num|
      enqueue(num.to_i)
    end

    @table.status
  end

  private

  def enqueue(num)
    @table.step
    if @table.empty?(num)
      @table.attache(num)
    else
      enqueue(num)
    end
  end
end
