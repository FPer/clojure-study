class Table
  EMPTY = 0
  WAIT = 1
  EATING = 2
  BREAK = 3

  def initialize(seats)
    @seats = seats
  end

  def empty?(num)
    num <= @seats.size && logical_seats.join.include?(EMPTY.to_s * num)
  end

  def attache(num)
    index = logical_seats.join.index(EMPTY.to_s * num)
    num.times do |i|
      index = index - @seats.size if i + index >= @seats.size
      @seats[index + i] = WAIT
    end
  end

  def step
    @seats.map! do |seat|
      case seat
      when EMPTY then
        EMPTY
      when BREAK then
        EMPTY
      else
        seat + 1
      end
    end
  end

  def to_s
    @seats.join
  end

  def status
    @seats.map {|seat| seat == EMPTY ? 0 : 1 }.join
  end

  def logical_seats
    @seats + @seats
  end
end
