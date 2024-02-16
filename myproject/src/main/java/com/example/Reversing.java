package com.example;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtIf;
import spoon.Launcher;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.reflect.CtModel;


public class Reversing {
    public static void transformModel(CtModel model) {
                model.getElements(new TypeFilter<>(CtIf.class)).forEach(ifStatement -> {
                CtExpression<Boolean> condition = ((CtIf) ifStatement).getCondition();
                CtExpression<Boolean> transformedCondition = reverseOperators(condition);
                ((CtIf) ifStatement).setCondition(transformedCondition);
               });
    }
    
      // print function
    public static void printAST(CtModel model, Launcher launcher) {
        DefaultJavaPrettyPrinter printer = new DefaultJavaPrettyPrinter(launcher.getEnvironment());
        model.getAllTypes().forEach(type -> {
            printer.scan(type);
            System.out.println(printer.toString());
        });
  }

    public static CtExpression<Boolean> reverseOperators(CtExpression<Boolean> condition) {
        if (condition instanceof CtBinaryOperator) {
            CtBinaryOperator<Boolean> binaryOperator = (CtBinaryOperator<Boolean>) condition;
            switch (binaryOperator.getKind()) {
                case EQ:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.NE);
                case NE:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.EQ);
                case AND:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.OR);
                case OR:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.AND);
                case GT:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.LE);
                case GE:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.LT);
                case LT:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.GE);
                case LE:
                    return binaryOperator.clone().setKind(BinaryOperatorKind.GT);
            }
        }
        return condition;
    }

}
