package cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaMethodInfo;

import org.eclipse.jdt.core.dom.*;

public class FieldChangeVisitor extends ASTVisitor {
	private ExpressionResolvedVisitor expressionResolvedVisitor = new ExpressionResolvedVisitor();
	private JavaMethodInfo method;

	public FieldChangeVisitor(JavaMethodInfo method) {
		this.method = method;
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(Assignment node) {
		Expression lhs = node.getLeftHandSide();
		lhs.accept(expressionResolvedVisitor);
		return true;
	}

	private class ExpressionResolvedVisitor extends ASTVisitor {
		@Override
		public boolean visit(ArrayAccess node) {
			return false;
		}

		@Override
		public boolean visit(ArrayCreation node) {
			return false;
		}

		@Override
		public boolean visit(ArrayInitializer node) {
			return false;
		}

		@Override
		public boolean visit(CastExpression node) {
			node.getExpression().accept(this);
			return false;
		}

		@Override
		public boolean visit(ClassInstanceCreation node) {
			return false;
		}

		@Override
		public boolean visit(ConditionalExpression node) {
			node.getThenExpression().accept(this);
			node.getElseExpression().accept(this);
			return false;
		}

		@Override
		public boolean visit(FieldAccess node) {
			// only process this.XXX and field.XXX
			Expression reciever = node.getExpression();
			SimpleName field = node.getName();
			if (reciever instanceof ThisExpression) {
				method.changeVariable(field.getFullyQualifiedName());
			} else {
				reciever.accept(this);
			}
			return false;
		}

		@Override
		public boolean visit(InfixExpression node) {
			return false;
		}

		@Override
		public boolean visit(MethodInvocation node) {
			return false;
		}

		@Override
		public boolean visit(QualifiedName node) {
			// only process field.XXX
			Name reciever = node.getQualifier();
			reciever.accept(this);
			return false;
		}

		@Override
		public boolean visit(SimpleName node) {
			method.changeVariable(node.getFullyQualifiedName());
			return false;
		}

		@Override
		public boolean visit(SuperFieldAccess node) {
			// only process field.XXX
			SimpleName field = node.getName();
			method.changeVariable(field.getFullyQualifiedName());
			return false;
		}

		@Override
		public boolean visit(SuperMethodInvocation node) {
			return false;
		}

	}
}
