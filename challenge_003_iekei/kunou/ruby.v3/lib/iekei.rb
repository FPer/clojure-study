class Iekei
  TABLE_SIZE = 8

  EMPTY = 0
  WAIT = 1
  EATING = 2
  BREAK = 3

  NOT_EMPTY = 1

  def comming(customer_numbers)
    table = [0].cycle(TABLE_SIZE).to_a
    customer_numbers.each_char do |num|
      table = enqueue(table, num.to_i)
    end

    table.map {|seat| seat == EMPTY ? EMPTY : NOT_EMPTY }.join
  end

  private

  def enqueue(table, num)
    table = step(table)
    empty?(table, num) ? attache(table, num) : enqueue(table, num)
  end

  def empty?(table, num)
    num <= table.size && logical_table(table).join.include?(EMPTY.to_s * num)
  end

  def attache(table, num)
    index = logical_table(table).join.index(EMPTY.to_s * num)
    target_indexes = num.times.map {|i| (index + i) % TABLE_SIZE }
    table.map.with_index {|seat, i| target_indexes.include?(i) ? WAIT : seat }
  end

  def step(table)
    table.map(&:succ).map {|seat| [EATING, BREAK].include?(seat) ? seat : EMPTY }
  end

  def logical_table(table)
    table + table
  end
end
