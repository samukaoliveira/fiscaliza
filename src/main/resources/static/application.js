document.addEventListener("DOMContentLoaded", function () {
    const select = document.getElementById("selectDependencia");
    const idSelecionado = select.dataset.selected;

    fetch('/atividade/dependencia')
        .then(response => {
            if (!response.ok) {
                Console.log(response.statusText)
                throw new Error("Erro ao pesquisar dependência");
            }
            return response.json();
        })
        .then(dados => {
            select.innerHTML = '<option value="">Selecione uma opção...</option>'

            dados.forEach(item => {
                const option = document.createElement('option');
                option.value = item.id;
                option.textContent = item.descricao;

                if (idSelecionado && String(item.id) === String(idSelecionado)) {
                    option.selected = true;
                }

                select.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Erro na requisição:", error);
            select.innerHTML = '<option value="">Erro ao carregar dados</option>';
        });
});