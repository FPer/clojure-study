require 'table'

class Iekei
  def initialize
    @table = Table.new([0, 0, 0, 0, 0, 0, 0, 0])
  end

  def comming(customer_numbers)
    customer_numbers.each_char do |num|
      attache(num.to_i)
    end

    @table.status
  end

  private

  def attache(num)
    @table.step
    if @table.empty?(num)
      @table.attache(num)
    else
      attache(num)
    end
  end
end
