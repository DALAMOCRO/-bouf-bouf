package com.boufbouf.app.feature.feed.data

import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.core.model.Recipe
import kotlinx.coroutines.delay

class MockFeedRepository : FeedRepository {
    override suspend fun getForYou(): List<FeedVideo> {
        delay(150)
        return MockFeedData.videos
    }

    override suspend fun getFollowing(): List<FeedVideo> = getForYou().filterIndexed { index, _ -> index % 2 == 0 }
}

/** Stable local catalogue used by the demo feed and recipe route. */
object MockFeedData {
    // Google-hosted Media3 demonstration assets; no third-party social content is used.
    private const val DEMO_VIDEO = "https://storage.googleapis.com/exoplayer-test-media-0/Android.mp4"

    val videos = listOf(
            video("rfissa", "Nadia", "@nadia.cuisine", "Rfissa express, comme chez nous.", listOf("#cuisinemarocaine", "#familial"), listOf("Msemen", "Poulet", "Lentilles"), 1840, 86, 0xFFC66A35, recipe("Rfissa express", 35, 4, listOf("Msemen", "Poulet", "Lentilles", "Ras el hanout"), listOf("Préparez la sauce parfumée.", "Ajoutez le poulet et les lentilles.", "Servez sur le msemen émietté."))),
            video("pasta", "Lina", "@linamange", "Des pâtes crémeuses en 15 minutes.", listOf("#rapide", "#diner"), listOf("Pâtes", "Citron", "Parmesan"), 921, 41, 0xFFD1844C, recipe("Pâtes citron parmesan", 15, 2, listOf("Pâtes", "Citron", "Parmesan", "Crème"), listOf("Cuisez les pâtes.", "Mélangez citron et crème.", "Ajoutez le parmesan hors du feu."))),
            video("chebakia", "Meryem", "@douceurs.meryem", "Chebakia dorée pour les tables de Ramadan.", listOf("#ramadan", "#dessert"), listOf("Miel", "Sésame", "Amande"), 2300, 122, 0xFFB25C32, recipe("Chebakia", 60, 8, listOf("Farine", "Sésame", "Miel", "Amandes"), listOf("Pétrissez la pâte.", "Formez les fleurs.", "Faites frire puis plongez dans le miel."))),
            video("omelette", "Yassine", "@yassine.en.cuisine", "L’astuce pour une omelette vraiment moelleuse.", listOf("#astuce", "#petitdejeuner"), listOf("Œufs", "Beurre", "Herbes"), 704, 29, 0xFFFFA85A, null),
            video("harira", "Oum Salma", "@oum.salma", "La harira de ma grand-mère, généreuse et parfumée.", listOf("#commecheznous", "#soupe"), listOf("Tomate", "Pois chiches", "Céleri"), 3321, 204, 0xFF9F4E31, recipe("Harira familiale", 50, 6, listOf("Tomates", "Pois chiches", "Lentilles", "Céleri"), listOf("Faites revenir les aromates.", "Ajoutez tomates et légumineuses.", "Laissez mijoter puis liez la soupe."))),
            video("ratatouille", "Inès", "@ines.au.soleil", "Ratatouille d’été, économique et colorée.", listOf("#vegetarien", "#economique"), listOf("Courgette", "Tomate", "Aubergine"), 1160, 63, 0xFFDE7851, recipe("Ratatouille", 35, 4, listOf("Courgettes", "Aubergines", "Tomates", "Oignons"), listOf("Découpez les légumes.", "Faites-les revenir séparément.", "Mijotez avec les tomates."))),
            video("sushi", "Kenji", "@kenji.table", "Des makis faciles avec ce que vous avez à la maison.", listOf("#cuisinedumonde", "#japon"), listOf("Riz", "Concombre", "Saumon"), 1530, 74, 0xFF587D84, recipe("Makis maison", 40, 3, listOf("Riz à sushi", "Nori", "Concombre", "Saumon"), listOf("Assaisonnez le riz.", "Garnissez la feuille nori.", "Roulez et découpez."))),
            video("briouates", "Soukaina", "@soukaina.b", "Briouates au fromage prêtes pour le ftour.", listOf("#ftour", "#ramadan"), listOf("Feuilles", "Fromage", "Menthe"), 1980, 91, 0xFFB7652B, recipe("Briouates fromage", 25, 5, listOf("Feuilles de brick", "Fromage", "Menthe", "Œuf"), listOf("Mélangez la farce.", "Pliez les briouates.", "Cuisez jusqu’à coloration."))),
            video("smoothie", "Adam", "@adam.frais", "Un smoothie mangue-banane sans sucre ajouté.", listOf("#boisson", "#rapide"), listOf("Mangue", "Banane", "Lait"), 610, 22, 0xFFE59B3E, recipe("Smoothie mangue-banane", 5, 2, listOf("Mangue", "Banane", "Lait", "Glaçons"), listOf("Placez tout dans le blender.", "Mixez jusqu’à texture lisse.", "Servez immédiatement."))),
            video("couscous", "Chef Rachid", "@chef.rachid", "Couscous aux sept légumes du vendredi.", listOf("#couscous", "#maroc"), listOf("Semoule", "Carotte", "Courgette"), 4820, 318, 0xFFCB7434, recipe("Couscous aux légumes", 75, 6, listOf("Semoule", "Carottes", "Courgettes", "Pois chiches"), listOf("Préparez le bouillon.", "Cuisez les légumes à la vapeur.", "Aérez la semoule et servez."))),
        )

    private fun video(
        id: String, creatorName: String, creatorHandle: String, description: String,
        hashtags: List<String>, ingredients: List<String>, likes: Int, comments: Int,
        color: Long, recipe: Recipe?,
    ) = FeedVideo(id, DEMO_VIDEO, creatorName, creatorHandle, description, hashtags, ingredients, likes, comments, recipe, color)

    private fun recipe(title: String, minutes: Int, servings: Int, ingredients: List<String>, steps: List<String>) =
        Recipe(title, minutes, servings, ingredients, steps)
}
