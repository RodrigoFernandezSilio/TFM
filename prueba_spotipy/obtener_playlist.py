import spotipy
from spotipy.oauth2 import SpotifyOAuth

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(
    client_id="485db6344f7845bd9bda3ac767e70aea",
    client_secret="ae22146c55f14ef89aad5c6727ad2e6c",
    redirect_uri="http://localhost:1234",
    scope="user-library-read"))

# Lista de géneros

genres = ['pop', 'rock', 'hip-hop', 'electronic', 'jazz', 'classical']

# Obtener listas de reproducción
playlists = {}
for genre in genres:
    results = sp.search(q=genre, type='playlist', limit=5)  # Cambia limit para más resultados
    playlists[genre] = results['playlists']['items']

# Imprimir listas de reproducción
for genre, items in playlists.items():
    print(f'\nListas de reproducción para {genre}:')
    for item in items:
        print(f"- {item['name']} (ID: {item['id']}) - {item['external_urls']['spotify']}")