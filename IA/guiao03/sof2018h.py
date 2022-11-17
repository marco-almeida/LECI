from bayes_net import *

bn = BayesNet()

variables = ['sc', 'pt', 'cp', 'fr', 'pa', 'cnl']

# sc - work overload / sobrecarga
# pt - usar processador de texto / use text processor
# cp - cara preocupada / look worried
# fr - frequencia rato / mouse frequency
# pa - precisa de ajuda / needs help
# cnl - acumular correio eletronico nao lido / accumulate unread email

bn.add('sc', [], 0.6)  # 60% dos utilizadores têm sobrecarga de trabalho

# 1% dos utilizadores com sobrecarga de trabalho têm cara preocupada e nao precisam de ajuda
bn.add('cp', [('sc', True), ('pa', False)], 0.01)
# 2% dos utilizadores com sobrecarga de trabalho têm cara preocupada e precisam de ajuda
bn.add('cp', [('sc', True), ('pa', True)], 0.02)
# 0.1% dos utilizadores sem sobrecarga de trabalho têm cara preocupada e nao precisam de ajuda
bn.add('cp', [('sc', False), ('pa', False)], 0.001)
# 1.1% dos utilizadores sem sobrecarga de trabalho têm cara preocupada e precisam de ajuda
bn.add('cp', [('sc', False), ('pa', True)], 0.011)


bn.add('cnl', [('sc', False)], 0.001) # 0.1% dos utilizadores sem sobrecarga de trabalho acumulam correio eletronico nao lido
bn.add('cnl', [('sc', True)], 0.9)

bn.add('pt', [], 0.05)

bn.add('pa', [('pt', True)], 0.25)
bn.add('pa', [('pt', False)], 0.004)

bn.add('fr', [('pt', False), ('pa', True)], 0.1)
bn.add('fr', [('pt', False), ('pa', False)], 0.01)
bn.add('fr', [('pa', True), ('pt', True)], 0.9)
bn.add('fr', [('pa', False), ('pt', True)], 0.9)

