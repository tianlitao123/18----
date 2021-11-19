import {
	createRouter,
	createWebHistory
} from 'vue-router'
import Main from '../views/Main.vue'
// import FirstPage from "../views/FirstPage";
import TotalRankList from "../views/TotalRankList"
import Description1 from '../components/Description1.vue'
import TotalChart from "../views/TotalChart"
import UserPage from "../views/UserPage.vue"
import ChinaMaps from "../views/ChinaMaps.vue"
import SearchPage from "../views/SearchPage.vue"
import cd from "../views/cd.vue"
const routes = [{
		path: '/',
		name: 'Main',
		components: {
			ap: Main,
		},
		// },
		// {
		// 	path: '/TotalRankList',
		// 	name: 'TotalRankList',
		// 	components: {
		// 		ap: TotalRankList
		// 	}
		// }, {
		// 	path: '/TotalChart',
		// 	name: 'TotalChart',
		// 	components: {
		// 		ap: TotalChart
		// 	}
		// },
		children: [{
				path: '',
				name: 'Description1',
				components: {
					ma: Description1
				}
			},
			{
				path: 'TotalChart',
				name: 'TotalChart',
				components: {
					ma: TotalChart
				}
			},{
				path: 'TotalRankList',
				name: 'TotalRankList',
				components: {
					ma: TotalRankList
				}
			},
			{
				path: 'ChinaMaps',
				name: 'ChinaMaps',
				components: {
					ma: ChinaMaps
				}
			},
			{
				path: 'UserPage',
				name: 'UserPage',
				components: {
					ma: UserPage
				}
			}
		]
	},
	{
		path: '/SearchPage',
		name: "SearchPage",
		components: {
			ap: SearchPage
		}
	}, {
		path: '/cd',
		name: "cd",
		components: {
			ap: cd
		}
	},
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
