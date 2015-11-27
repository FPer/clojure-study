require 'table'

class Iekei
  def initialize
    @table = Table.new([0, 0, 0, 0, 0, 0, 0, 0])
  end

  def comming(customer_numbers)
    customer_numbers.each_char do |num|
      #puts '###num:' + num
      attache(num.to_i)
    end

    @table.status
  end

  private

  def attache(num)
    #puts 'before:' + @table.to_s
    @table.step
    #puts 'after:' + @table.to_s
    if @table.empty?(num)
      @table.attache(num)
    else
      attache(num)
    end
  end
end
