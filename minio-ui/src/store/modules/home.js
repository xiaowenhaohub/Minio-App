import { searchFile } from '@/api/home'


const state = {
    fileList: []
}

const mutations = {
    SET_FILE: (state, fileList) => {
        state.fileList = fileList
    },
}

const actions = {
    searchFile({ commit }, fileName) {
        return new Promise((resolve, reject) => {
            searchFile(fileName).then(response => {
                const { data } = response
                commit('SET_FILE', data)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },


}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
