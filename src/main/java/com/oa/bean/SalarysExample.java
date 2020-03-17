package com.oa.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalarysExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalarysExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEIdIsNull() {
            addCriterion("e_id is null");
            return (Criteria) this;
        }

        public Criteria andEIdIsNotNull() {
            addCriterion("e_id is not null");
            return (Criteria) this;
        }

        public Criteria andEIdEqualTo(String value) {
            addCriterion("e_id =", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotEqualTo(String value) {
            addCriterion("e_id <>", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThan(String value) {
            addCriterion("e_id >", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThanOrEqualTo(String value) {
            addCriterion("e_id >=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThan(String value) {
            addCriterion("e_id <", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThanOrEqualTo(String value) {
            addCriterion("e_id <=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLike(String value) {
            addCriterion("e_id like", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotLike(String value) {
            addCriterion("e_id not like", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdIn(List<String> values) {
            addCriterion("e_id in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotIn(List<String> values) {
            addCriterion("e_id not in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdBetween(String value1, String value2) {
            addCriterion("e_id between", value1, value2, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotBetween(String value1, String value2) {
            addCriterion("e_id not between", value1, value2, "eId");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNull() {
            addCriterion("serial_num is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("serial_num is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("serial_num =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("serial_num <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("serial_num >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("serial_num >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("serial_num <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("serial_num <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("serial_num like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("serial_num not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("serial_num in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("serial_num not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("serial_num between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("serial_num not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andBasicWageIsNull() {
            addCriterion("basic_wage is null");
            return (Criteria) this;
        }

        public Criteria andBasicWageIsNotNull() {
            addCriterion("basic_wage is not null");
            return (Criteria) this;
        }

        public Criteria andBasicWageEqualTo(String value) {
            addCriterion("basic_wage =", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageNotEqualTo(String value) {
            addCriterion("basic_wage <>", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageGreaterThan(String value) {
            addCriterion("basic_wage >", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageGreaterThanOrEqualTo(String value) {
            addCriterion("basic_wage >=", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageLessThan(String value) {
            addCriterion("basic_wage <", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageLessThanOrEqualTo(String value) {
            addCriterion("basic_wage <=", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageLike(String value) {
            addCriterion("basic_wage like", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageNotLike(String value) {
            addCriterion("basic_wage not like", value, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageIn(List<String> values) {
            addCriterion("basic_wage in", values, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageNotIn(List<String> values) {
            addCriterion("basic_wage not in", values, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageBetween(String value1, String value2) {
            addCriterion("basic_wage between", value1, value2, "basicWage");
            return (Criteria) this;
        }

        public Criteria andBasicWageNotBetween(String value1, String value2) {
            addCriterion("basic_wage not between", value1, value2, "basicWage");
            return (Criteria) this;
        }

        public Criteria andRoyaltyIsNull() {
            addCriterion("royalty is null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyIsNotNull() {
            addCriterion("royalty is not null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyEqualTo(Double value) {
            addCriterion("royalty =", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyNotEqualTo(Double value) {
            addCriterion("royalty <>", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyGreaterThan(Double value) {
            addCriterion("royalty >", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyGreaterThanOrEqualTo(Double value) {
            addCriterion("royalty >=", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyLessThan(Double value) {
            addCriterion("royalty <", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyLessThanOrEqualTo(Double value) {
            addCriterion("royalty <=", value, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyIn(List<Double> values) {
            addCriterion("royalty in", values, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyNotIn(List<Double> values) {
            addCriterion("royalty not in", values, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyBetween(Double value1, Double value2) {
            addCriterion("royalty between", value1, value2, "royalty");
            return (Criteria) this;
        }

        public Criteria andRoyaltyNotBetween(Double value1, Double value2) {
            addCriterion("royalty not between", value1, value2, "royalty");
            return (Criteria) this;
        }

        public Criteria andWorkDaysIsNull() {
            addCriterion("work_days is null");
            return (Criteria) this;
        }

        public Criteria andWorkDaysIsNotNull() {
            addCriterion("work_days is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDaysEqualTo(Integer value) {
            addCriterion("work_days =", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysNotEqualTo(Integer value) {
            addCriterion("work_days <>", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysGreaterThan(Integer value) {
            addCriterion("work_days >", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_days >=", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysLessThan(Integer value) {
            addCriterion("work_days <", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysLessThanOrEqualTo(Integer value) {
            addCriterion("work_days <=", value, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysIn(List<Integer> values) {
            addCriterion("work_days in", values, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysNotIn(List<Integer> values) {
            addCriterion("work_days not in", values, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysBetween(Integer value1, Integer value2) {
            addCriterion("work_days between", value1, value2, "workDays");
            return (Criteria) this;
        }

        public Criteria andWorkDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("work_days not between", value1, value2, "workDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysIsNull() {
            addCriterion("leave_early_days is null");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysIsNotNull() {
            addCriterion("leave_early_days is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysEqualTo(Integer value) {
            addCriterion("leave_early_days =", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysNotEqualTo(Integer value) {
            addCriterion("leave_early_days <>", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysGreaterThan(Integer value) {
            addCriterion("leave_early_days >", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_early_days >=", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysLessThan(Integer value) {
            addCriterion("leave_early_days <", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysLessThanOrEqualTo(Integer value) {
            addCriterion("leave_early_days <=", value, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysIn(List<Integer> values) {
            addCriterion("leave_early_days in", values, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysNotIn(List<Integer> values) {
            addCriterion("leave_early_days not in", values, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysBetween(Integer value1, Integer value2) {
            addCriterion("leave_early_days between", value1, value2, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andLeaveEarlyDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_early_days not between", value1, value2, "leaveEarlyDays");
            return (Criteria) this;
        }

        public Criteria andIssueTimeIsNull() {
            addCriterion("issue_time is null");
            return (Criteria) this;
        }

        public Criteria andIssueTimeIsNotNull() {
            addCriterion("issue_time is not null");
            return (Criteria) this;
        }

        public Criteria andIssueTimeEqualTo(Date value) {
            addCriterion("issue_time =", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeNotEqualTo(Date value) {
            addCriterion("issue_time <>", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeGreaterThan(Date value) {
            addCriterion("issue_time >", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("issue_time >=", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeLessThan(Date value) {
            addCriterion("issue_time <", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeLessThanOrEqualTo(Date value) {
            addCriterion("issue_time <=", value, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeIn(List<Date> values) {
            addCriterion("issue_time in", values, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeNotIn(List<Date> values) {
            addCriterion("issue_time not in", values, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeBetween(Date value1, Date value2) {
            addCriterion("issue_time between", value1, value2, "issueTime");
            return (Criteria) this;
        }

        public Criteria andIssueTimeNotBetween(Date value1, Date value2) {
            addCriterion("issue_time not between", value1, value2, "issueTime");
            return (Criteria) this;
        }

        public Criteria andAccountOtherIsNull() {
            addCriterion("account_other is null");
            return (Criteria) this;
        }

        public Criteria andAccountOtherIsNotNull() {
            addCriterion("account_other is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOtherEqualTo(String value) {
            addCriterion("account_other =", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherNotEqualTo(String value) {
            addCriterion("account_other <>", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherGreaterThan(String value) {
            addCriterion("account_other >", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherGreaterThanOrEqualTo(String value) {
            addCriterion("account_other >=", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherLessThan(String value) {
            addCriterion("account_other <", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherLessThanOrEqualTo(String value) {
            addCriterion("account_other <=", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherLike(String value) {
            addCriterion("account_other like", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherNotLike(String value) {
            addCriterion("account_other not like", value, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherIn(List<String> values) {
            addCriterion("account_other in", values, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherNotIn(List<String> values) {
            addCriterion("account_other not in", values, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherBetween(String value1, String value2) {
            addCriterion("account_other between", value1, value2, "accountOther");
            return (Criteria) this;
        }

        public Criteria andAccountOtherNotBetween(String value1, String value2) {
            addCriterion("account_other not between", value1, value2, "accountOther");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(Double value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(Double value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(Double value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(Double value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(Double value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(Double value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<Double> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<Double> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(Double value1, Double value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(Double value1, Double value2) {
            addCriterion("salary not between", value1, value2, "salary");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}