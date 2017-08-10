package yanbinwa.designPattern.Interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter
{
    interface Expression 
    {
        public int interpret(final Map<String, Expression> variables);
    }
    
    class Number implements Expression
    {

        private int number;
        
        public Number(int number)
        {
            this.number = number;
        }
        
        @Override
        public int interpret(Map<String, Expression> variables)
        {
            return number;
        }
        
    }
    
    class Plus implements Expression
    {

        private Expression left;
        private Expression right;
        
        public Plus(Expression left, Expression right)
        {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int interpret(Map<String, Expression> variables)
        {
            return left.interpret(variables) + right.interpret(variables);
        }
        
    }
    
    class Minus implements Expression
    {

        private Expression left;
        private Expression right;
        
        public Minus(Expression left, Expression right)
        {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int interpret(Map<String, Expression> variables)
        {
            return left.interpret(variables) - right.interpret(variables);
        }
        
    }
    
    class Variable implements Expression
    {
        String name;
        
        public Variable(String name)
        {
            this.name = name;
        }
        
        @Override
        public int interpret(Map<String, Expression> variables)
        {
            if (variables != null && variables.get(name) != null)
            {
                return variables.get(name).interpret(variables);
            }
            return 0;
        }
        
    }
    
    class Evaluator implements Expression
    {
        private Expression syntaxTree;
        
        public Evaluator(final String expression)
        {
            final Stack<Expression> expressionStack = new Stack<Expression>();
            for (final String token : expression.split(" ")) {
                if (token.equals("+"))
                {
                    Plus plus = new Plus(expressionStack.pop(), expressionStack.pop());
                    expressionStack.push(plus);
                }
                else if(token.equals("-"))
                {
                    Expression right = expressionStack.pop();
                    Expression left = expressionStack.pop();
                    Minus minus = new Minus(left, right);
                    expressionStack.push(minus);
                }
                else
                {
                    try
                    {
                        int value = Integer.parseInt(token);
                        Expression number = new Number(value);
                        expressionStack.push(number);
                    }
                    catch(NumberFormatException e)
                    {
                        Expression variable = new Variable(token);
                        expressionStack.push(variable);
                    }
                }
            }
            syntaxTree = expressionStack.pop();
        }

        @Override
        public int interpret(Map<String, Expression> variables)
        {
            return syntaxTree.interpret(variables);
        }
    }
    
    public void test()
    {
        //x + 1 - y + z   x = 1 y = 2 z = 3
        String expression = "x 1 + y - z +";
        Expression evaluator = new Evaluator(expression);
        Map<String, Expression> variables = new HashMap<String, Expression>();
        variables.put("x", new Number(1));
        variables.put("y", new Number(4));
        variables.put("z", new Number(9));
        System.out.println(evaluator.interpret(variables));
    }
    
    public static void main(String[] args)
    {
        new Interpreter().test();
    }
}
