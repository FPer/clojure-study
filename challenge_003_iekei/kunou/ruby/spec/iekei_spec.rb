require 'iekei'

describe Iekei do
  describe 'comming' do
    let(:iekei) { Iekei.new }

    it { expect(iekei.comming('3316')).to eq '11111110' }
    it { expect(iekei.comming('3342153')).to eq '11111111'}
    it { expect(iekei.comming('8')).to eq '11111111'}
    it { expect(iekei.comming('232624')).to eq '11110110'}
    it { expect(iekei.comming('1')).to eq '10000000'}
    it { expect(iekei.comming('224673432')).to eq '11111000'}
    it { expect(iekei.comming('123464334')).to eq '11111110'}
    it { expect(iekei.comming('44372332')).to eq '11111111'}
    it { expect(iekei.comming('2344')).to eq '11111111'}
    it { expect(iekei.comming('6448476233')).to eq '11111100'}
    it { expect(iekei.comming('4345174644')).to eq '11111111'}
    it { expect(iekei.comming('77743')).to eq '11111110'}
    it { expect(iekei.comming('2136524281')).to eq '10000000'}
    it { expect(iekei.comming('344373')).to eq '11100000'}
    it { expect(iekei.comming('434226')).to eq '11111111'}
    it { expect(iekei.comming('7433223')).to eq '11111110'}
    it { expect(iekei.comming('2246534')).to eq '11110111'}
    it { expect(iekei.comming('634')).to eq '11111110'}
    it { expect(iekei.comming('2222')).to eq '11111100'}
    it { expect(iekei.comming('2442343238')).to eq '11111111'}
    it { expect(iekei.comming('7243344')).to eq '11111111'}
    it { expect(iekei.comming('26147234')).to eq '10111111'}
    it { expect(iekei.comming('34424')).to eq '10011111'}
    it { expect(iekei.comming('6334')).to eq '11011111'}
    it { expect(iekei.comming('3828342')).to eq '11011110'}
    it { expect(iekei.comming('4431')).to eq '11110000'}
    it { expect(iekei.comming('2843212125')).to eq '11111111'}
    it { expect(iekei.comming('333336482')).to eq '11000000'}
    it { expect(iekei.comming('374')).to eq '11110000'}
    it { expect(iekei.comming('4382333')).to eq '11100111'}
  end
end
