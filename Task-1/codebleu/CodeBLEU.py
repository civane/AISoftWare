import re
from collections import Counter
import math
import javalang

# n-gram calculate
def tokenize_code(code):
    # Simple tokenization: Use regular expressions to match words, numbers, symbols, etc.
    tokens = re.findall(r'\w+|[^\s\w]', code)
    return tokens

def get_ngrams(tokens, n):
    return [tuple(tokens[i:i + n]) for i in range(len(tokens) - n + 1)]

def ngram_precision(candidate_ngrams, reference_ngrams):
    # n-gram
    cand_counts = Counter(candidate_ngrams)
    ref_counts = Counter(reference_ngrams)
    overlap = 0
    for ng in cand_counts:
        overlap += min(cand_counts[ng], ref_counts.get(ng, 0))
    total = max(len(candidate_ngrams), 1)
    return overlap / total

def brevity_penalty(candidate_len, reference_len):
    if candidate_len > reference_len:
        return 1
    elif candidate_len == 0:
        return 0
    else:
        return math.exp(1 - reference_len / candidate_len)

def compute_bleu(candidate_code, reference_code, n=4):
    candidate_tokens = tokenize_code(candidate_code)
    reference_tokens = tokenize_code(reference_code)

    precisions = []
    for i in range(1, n + 1):
        c_ngrams = get_ngrams(candidate_tokens, i)
        r_ngrams = get_ngrams(reference_tokens, i)
        prec = ngram_precision(c_ngrams, r_ngrams)
        precisions.append(prec)

    # geo_mean
    if min(precisions) == 0:
        geo_mean = 0
    else:
        geo_mean = math.exp(sum(math.log(p) for p in precisions) / n)

    bp = brevity_penalty(len(candidate_tokens), len(reference_tokens))
    bleu = bp * geo_mean
    return bleu

# syntax-aware AST
def parse_java_ast(code):
    # 解析Java代码生成AST树
    tree = javalang.parse.parse(code)
    return tree

def extract_ast_tokens(node):
    # Recursively traverse the AST, extracting the node type names as a token sequence.
    tokens = []
    def visit(node):
        if isinstance(node, javalang.ast.Node):
            tokens.append(type(node).__name__)
            for child in node.children:
                if isinstance(child, list):
                    for c in child:
                        visit(c)
                elif child is not None:
                    visit(child)
    visit(node)
    return tokens

def ast_ngram_match(candidate_code, reference_code, n=3):
    cand_tree = parse_java_ast(candidate_code)
    ref_tree = parse_java_ast(reference_code)
    cand_tokens = extract_ast_tokens(cand_tree)
    ref_tokens = extract_ast_tokens(ref_tree)

    cand_ngrams = get_ngrams(cand_tokens, n)
    ref_ngrams = get_ngrams(ref_tokens, n)

    cand_counts = Counter(cand_ngrams)
    ref_counts = Counter(ref_ngrams)

    overlap = 0
    for ng in cand_counts:
        overlap += min(cand_counts[ng], ref_counts.get(ng, 0))
    total = max(len(cand_ngrams), 1)
    score = overlap / total
    return score

# semantic similarity
def extract_semantic_features(tree):
    # Extract node types and variable names from the AST as semantic features
    node_types = []
    variable_names = []

    def visit(node):
        if isinstance(node, javalang.ast.Node):
            node_types.append(type(node).__name__)
            # variable-name extraction (for VariableDeclarator nodes).
            if hasattr(node, 'name') and isinstance(node.name, str):
                variable_names.append(node.name)
            for child in node.children:
                if isinstance(child, list):
                    for c in child:
                        visit(c)
                elif child is not None:
                    visit(child)

    visit(tree)
    return node_types, variable_names

def jaccard_similarity(list1, list2):
    set1, set2 = set(list1), set(list2)
    intersection = len(set1.intersection(set2))
    union = len(set1.union(set2))
    return intersection / union if union != 0 else 0

def semantic_similarity(candidate_code, reference_code):
    cand_tree = parse_java_ast(candidate_code)
    ref_tree = parse_java_ast(reference_code)

    cand_node_types, cand_vars = extract_semantic_features(cand_tree)
    ref_node_types, ref_vars = extract_semantic_features(ref_tree)

    node_type_sim = jaccard_similarity(cand_node_types, ref_node_types)
    var_sim = jaccard_similarity(cand_vars, ref_vars)

    # Simple weighted synthesis of semantic similarity
    semantic_sim_score = 0.7 * node_type_sim + 0.3 * var_sim
    return semantic_sim_score


def main():
    candidate_code = """
    class Solution {
        public String minCostGoodCaption(String caption) {
            int i = 9;
        }
    }
    """

    reference_code = """
    class Solution {
        public String minCostGoodCaption(String caption) {
            int i = 9;
        }
    }
    """
    bleu_score = compute_bleu(candidate_code, reference_code,n=4)
    print(f"CodeBLEU中的n-gram BLEU得分: {bleu_score:.4f}")

    score = ast_ngram_match(candidate_code, reference_code, n=3)
    print(f"基于AST的n-gram匹配得分: {score:.4f}")

    score = semantic_similarity(candidate_code, reference_code)
    print(f"基于AST和变量的语义相似度得分: {score:.4f}")

if __name__ == "__main__":
    main()