class Table

  EMPTY = 0
  WAIT = 1
  EATING = 2
  BREAK = 3

  def initialize(seats)
    @seats = seats
  end

  def empty?(num)
    if @seats.join.include?(EMPTY.to_s * num)
      true
    else
      first_empty_size = empty_size
      last_empty_size = empty_size(:reverse_each)

      return false if !first_empty_size || !last_empty_size

      empty_size = first_empty_size + last_empty_size

      empty_size >= num
    end
  end

  def attache(num)
    if @seats.join.include?(EMPTY.to_s * num)
      index = @seats.join.index(EMPTY.to_s * num)
      num.times do |i|
        @seats[index + i] = WAIT
      end
    else
      pre_empty_size = empty_size(:reverse_each)
      num.times do |i|
        if i < pre_empty_size
          @seats[(@seats.size - empty_size(:reverse_each))] = WAIT
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

  def empty_size(method = :each)
    @seats.send(method).with_index do |element, index|
      if element != EMPTY
        return index
      end
    end

    return nil
  end
end
