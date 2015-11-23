import Data.List

data Status = Empty | Wait | Eating | Rest deriving (Eq, Show)

timePassed Empty  = Empty
timePassed Wait   = Eating
timePassed Eating = Rest
timePassed Rest   = Empty

rotate seats =
       (tail seats) ++ [head seats]

judge _ result 0 = result
judge seats result num =
   judge seats' result' (num - 1)
     where
       seats' = rotate seats
       result' = zipWith (&&) (map (\a -> a == Empty) seats) result
  
findSeatNo seats num =
  findIndex (== True) result
    where result = judge seats (take 8 (repeat True)) num

sitdown seats num =
  case findSeatNo seats num of
    Nothing -> sitdown (map timePassed seats) num
    Just n -> (take n seats) ++ (take num (repeat Wait)) ++ (drop (n + num) seats)
    

-- doProcess [Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty] [3, 3, 1, 6]
doProcess seats [] = seats
doProcess seats nums = do
  doProcess (sitdown (map timePassed seats) (head nums)) (tail nums)

