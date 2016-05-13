package kadai5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<Map<String, Object>> outDataList = new ArrayList<>();

		createInDataList().stream().forEach(inData -> {

			Map<String, Object> ct = getData(inData, outDataList, "id", "ctKey", (data) -> createCt(data));
			List<Map<String, Object>> ctValues = (List<Map<String, Object>>) ct.get("ctValues");

			Map<String, Object> group = getData(inData, ctValues, "order", "groupKey", (data) -> createGroup(data));
			List<Map<String, String>> groupValues = (List<Map<String, String>>) group.get("groupValues");

			groupValues.add(createItem(inData));

		});

		System.out.println(outDataList);

	}

	public static Map<String, Object> getData(final Map<String, String> inData, final List<Map<String, Object>> dataList,
			final String inDataKey, final String dataKey, final Function<Map<String, String>, Map<String, Object>> createFunction) {

		Optional<Map<String, Object>> optionalValue = dataList.stream()
				.filter(data -> inData.get(inDataKey).equals(data.get(dataKey))).findFirst();

		Map<String, Object> value = optionalValue.orElseGet(() -> {
			Map<String, Object> newValue = createFunction.apply(inData);
			dataList.add(newValue);
			return newValue;
		});

		return value;
	}

	public static Map<String, Object> createCt(final Map<String, String> inData) {
		Map<String, Object> ct = new HashMap<>();

		ct.put("ctKey", inData.get("id"));
		ct.put("ctName", inData.get("name"));
		ct.put("ctValues", new ArrayList<>());

		return ct;
	}

	public static Map<String, Object> createGroup(final Map<String, String> inData) {
		Map<String, Object> group = new HashMap<>();

		group.put("groupKey", inData.get("order"));
		group.put("date", inData.get("date"));
		group.put("groupValues", new ArrayList<>());

		return group;
	}

	public static Map<String, String> createItem(final Map<String, String> inData) {
		Map<String, String> item = new HashMap<>();

		item.put("itemKey", inData.get("item"));
		item.put("amount", inData.get("amount"));
		item.put("count", inData.get("count"));

		return item;
	}

	public static List<Map<String, String>> createInDataList() {
		List<Map<String, String>> inData = new ArrayList<>();

		inData.add(createInData("1", "a", "1", "01/01", "aaa", "1", "100"));
		inData.add(createInData("1", "a", "1", "01/01", "bbb", "2", "200"));
		inData.add(createInData("1", "a", "1", "01/01", "ccc", "3", "300"));
		inData.add(createInData("1", "a", "2", "01/02", "aaa", "1", "100"));
		inData.add(createInData("1", "a", "2", "01/02", "bbb", "2", "200"));
		inData.add(createInData("1", "a", "3", "01/03", "ccc", "3", "300"));
		inData.add(createInData("2", "b", "4", "02/01", "aaa", "1", "100"));
		inData.add(createInData("2", "b", "4", "02/01", "bbb", "2", "200"));
		inData.add(createInData("3", "c", "5", "03/01", "ccc", "3", "300"));

		return inData;
	}

	public static Map<String, String> createInData(final String id, final String name, final String order, final String date, final String item,
			final String count, final String amount) {

		Map<String, String> data = new HashMap<>();
		data.put("id", id);
		data.put("name", name);
		data.put("order", order);
		data.put("date", date);
		data.put("item", item);
		data.put("count", count);
		data.put("amount", amount);

		return data;
	}
}
