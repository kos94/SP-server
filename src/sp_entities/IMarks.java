package sp_entities;

public interface IMarks {
	public static final int MIN_MARK[] = { 30, 30, 60 };
	public static final int ABSENT = -1;
	public void sortByFirstColumn();
	public void countAggregation();
}
