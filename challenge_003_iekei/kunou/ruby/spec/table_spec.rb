require 'table'

describe Table do
  describe 'empty?' do
    context 'all empty' do
      let(:table) { Table.new([0, 0, 0, 0, 0, 0, 0, 0]) }

      it { expect(table.empty?(8)).to be_truthy }
      it { expect(table.empty?(9)).to be_falsy }
    end

    context 'part empty' do
      let(:table) { Table.new([1, 1, 1, 1, 0, 0, 0, 0]) }

      it { expect(table.empty?(4)).to be_truthy }
      it { expect(table.empty?(5)).to be_falsy }
    end

    context 'part empty2' do
      let(:table) { Table.new([0, 1, 1, 1, 1, 1, 0, 0]) }

      it { expect(table.empty?(3)).to be_truthy }
      it { expect(table.empty?(4)).to be_falsy }
    end
  end

  describe 'empty_size' do
    let(:table) { Table.new([0, 1, 1, 1, 1, 1, 0, 0]) }

    it { expect(table.empty_size).to eq 1 }
  end

  describe 'empty_size last' do
    let(:table) { Table.new([0, 1, 1, 1, 1, 1, 0, 0]) }

    it { expect(table.empty_size(:reverse_each)).to eq 2 }
  end

  describe 'attache' do
    subject { table.to_s }

    context 'pattarn 1' do
      let(:table) { Table.new([0, 0, 1, 1, 1, 1, 0, 0]) }

      before do
        table.attache(2)
      end

      it { should eq '11111100' }
    end

    context 'pattarn 2' do
      let(:table) { Table.new([0, 1, 1, 1, 1, 0, 0, 0]) }

      before do
        table.attache(2)
      end

      it { should eq '01111110' }
    end

    context 'pattarn 3' do
      let(:table) { Table.new([0, 0, 0, 1, 1, 0, 0, 0]) }

      before do
        table.attache(5)
      end

      it { should eq '11011111' }
    end

    context 'pattarn 4' do
      let(:table) { Table.new([0, 0, 1, 1, 1, 1, 0, 0]) }

      before do
        table.attache(4)
      end

      it { should eq '11111111' }
    end
  end

  describe 'step' do
    subject { table.to_s }

    let(:table) { Table.new([0, 0, 1, 2, 3, 1, 0, 0]) }

    before do
      table.step
    end

    it { should eq '00230200' }
  end
end
