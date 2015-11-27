class Table

  EMPTY = 0
  WAIT = 1
  EATING = 2
  BREAK = 3

  def initialize(seats)
    @seats = seats
  end

  def empty?(num)
    if /0{#{num}}/ === @seats.join
      true
    else
      empty_size = not_empty_first + not_empty_last

      empty_size >= num
    end
  end

  def attache(num)
    if /0{#{num}}/ === @seats.join
      index = @seats.index(0)
      num.times do |i|
        @seats[index + i] = WAIT
      end
    else
      pre_empty_size = not_empty_last
      num.times do |i|
        if i < pre_empty_size
          @seats[(@seats.size - not_empty_last)] = WAIT
        end
      end

      if num - pre_empty_size >= 0
        attache(num - pre_empty_size)
      end
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
    tmp = []
    @seats.each do |seat|
      tmp << (seat == EMPTY ? 0 : 1)
    end
    tmp.join
  end

  def not_empty_first
    @seats.each_with_index do |element, index|
      if element != EMPTY
        return index
      end
    end
  end

  def not_empty_last
    @seats.reverse_each.with_index do |element, index|
      if element !=  EMPTY
       return index
      end
    end
  end
end
