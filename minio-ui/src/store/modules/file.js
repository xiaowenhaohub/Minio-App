import { searchFile, getFileList } from '@/api/home'


const state = {
    fileList: [],
    dirInfo: {},
    loading: false,
    uploading: false,
    fileStateList: [],
    openDownloadWindows: false
}

const mutations = {
    SET_FILE: (state, fileList) => {
        state.fileList = fileList
    },
    SET_DIR: (state, dirInfo) => {
        state.dirInfo = dirInfo
    },
    SET_LOADING: (state, loading) => {
        state.loading = loading
    },
    SET_UPLOAD: (state, uploading) => {
        state.uploading = uploading
    },
    PUSH_FILE: (state, fileState) => {
        state.fileStateList.push(fileState)
    },
    SET_STATE: (state, { index, percentage, nowSize }) => {
        state.fileStateList[index].percentage = percentage
        state.fileStateList[index].nowSize = nowSize
    },
    DELETE_STATE: (state, index) => {
        state.fileStateList.splice(index, 1)
    },
    SET_WINDOWS: (state, showState) => {
        state.openDownloadWindows = showState
    }
}

const actions = {

    removeFileStateList({ commit }, index) {
        commit('DELETE_STATE', index)
    },
    setPercentage({ commit }, { index, percentage, nowSize }) {
        commit('SET_STATE', { index, percentage, nowSize })
    },

    setOpenDownloadWindows({ commit }, showState) {
        commit('SET_WINDOWS', showState)
    },

    pushFileState({ commit }, fileState) {
        commit('PUSH_FILE', fileState)
    },

    setUploading({ commit }, uploading) {
        commit('SET_UPLOAD', uploading)
    },

    setLoading({ commit }, loading) {
        commit('SET_LOADING', loading)
    },

    searchFile({ commit }, fileName) {
        commit('SET_LOADING', true)
        return new Promise((resolve, reject) => {
            searchFile(fileName).then(response => {
                const { data } = response
                console.log(response)
                commit('SET_FILE', data)
                commit('SET_LOADING', false)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    getFileList({ commit }, dirId) {
        // commit('SET_LOADING', true)
        commit('SET_LOADING', true)
        return new Promise((resolve, reject) => {
            getFileList(dirId).then(response => {
                const { data } = response
                commit('SET_FILE', data.fileList)
                commit('SET_DIR', data.dirInfo)
                commit('SET_LOADING', false)

                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    }


}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
